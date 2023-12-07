package Terminal;

import FileCommands.Command;
import Terminal.RunInstance;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CommandParser {
    public static void parseCommand(RunInstance main, String input) {
        String[] args = input.split(" ");
        try {
//            find the command
            Class<?> clazz = Class.forName("FileCommands."+args[0]);
//            determine the parameters
            Class<?>[] parameterTypes = new Class<?>[2];
            parameterTypes[0] = RunInstance.class;
            parameterTypes[1] = String[].class;
//            create instance and get the method
            Object object = clazz.newInstance();
            Method method = clazz.getMethod("exec", parameterTypes);
//            invoke that method
            method.invoke(object, main, args);

        } catch(ClassNotFoundException e) {
            System.out.println("No such command found");
        } catch(NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
