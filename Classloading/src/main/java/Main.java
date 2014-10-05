import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class);

    private static int i = 0;

    private static final String[] shapes = new String[]{"Sircle", "Triangle"};

    private static ShapeClassLoader classLoader = new ShapeClassLoader();

    private static Shape shape;


    public static void main(String[] args) {

        while(true) {

            printMenu();

            try {
                String selected = new BufferedReader(new InputStreamReader(System.in)).readLine();
                Integer selectedInt = Integer.parseInt(selected);

                LOGGER.info("You selected: " + selected);

                switch (selectedInt) {

                    case 1:
                        loadNewShape();
                        break;
                    case 2:
                        drawShape();
                        break;
                    case 3:
                        System.exit(0);
                        break;

                    default: throw new IllegalArgumentException("You entered invalid symbol");
                }

            } catch(Exception e) {
                LOGGER.warn("Something bad happened =( ", e);
            }
        }
    }

    private static void drawShape() {
        if (shape != null) {
            shape.draw();
        } else {
            LOGGER.warn("Can't print anything: no shapes were loaded") ;
        }
    }

    private static void loadNewShape() throws IllegalAccessException, InstantiationException {
        Class clazz = classLoader.findClass(shapes[i]);
        if (i == shapes.length - 1) {
            i = 0;
        } else {
            i++;
        }
        shape = (Shape) clazz.newInstance();
    }

    private static void printMenu() {

        LOGGER.info("1. Load new shape.");
        LOGGER.info("2. Print shape.");
        LOGGER.info("3. Exit.");
    }
}
