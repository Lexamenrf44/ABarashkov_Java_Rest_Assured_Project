package io.swagger.petstore.jupiter;

import io.swagger.petstore.dtos.user.UserJson;
import io.swagger.petstore.service.UserController;
import io.swagger.petstore.utils.JsonMapper;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;

import java.util.Optional;

public class CreateUserExtension implements BeforeEachCallback, ParameterResolver, AfterEachCallback {

    private final UserController userController = new UserController();
    public static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(CreateUserExtension.class);

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        Optional<CreateUser> annotation = AnnotationSupport.findAnnotation(context.getRequiredTestMethod(), CreateUser.class);

        UserJson user = JsonMapper.mapToJson("user", UserJson.class);

        if (!annotation.get().userName().isEmpty()) {
            user.setUsername(annotation.get().userName());
        }

        if (annotation.isPresent()) {
            userController.createUser(user);
            context.getStore(NAMESPACE).put(context.getUniqueId(), user);
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(UserJson.class);
    }

    @Override
    public UserJson resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getStore(NAMESPACE).get(extensionContext.getUniqueId(), UserJson.class);
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        Optional<CreateUser> annotation = AnnotationSupport.findAnnotation(context.getRequiredTestMethod(), CreateUser.class);
        if (annotation.isPresent() && annotation.get().cleanUp()) {
            UserJson user = context.getStore(NAMESPACE).get(context.getUniqueId(), UserJson.class);

            if (user != null && user.getUsername() != null) {
                userController.deleteUserByUsername(user.getUsername());
            }
        }
    }
}
