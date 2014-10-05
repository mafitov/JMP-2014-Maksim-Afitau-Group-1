import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.log4j.Logger;

public class ShapeClassLoader extends ClassLoader {

    private static final Logger log = Logger.getLogger(ShapeClassLoader.class);

    private final String jarFile = "d:/prog/jmp/Functionality/target/Functionality-1.0-SNAPSHOT.jar";

    private Hashtable classes = new Hashtable();

    public ShapeClassLoader(ClassLoader parent) {
        super(parent);
    }

    public ShapeClassLoader() {
        this(ShapeClassLoader.class.getClassLoader());
    }

    @Override
    public Class findClass(String className) {
        byte classByte[];
        Class result = null;

        result = (Class) classes.get(className);

        if (result != null) {
            return result;
        }

        try {
            return findSystemClass(className);
        } catch (ClassNotFoundException e) {
            // just go further if class not found
        }

        try {
            JarFile jar = new JarFile(jarFile);
            JarEntry entry = jar.getJarEntry(className + ".class");
            InputStream is = jar.getInputStream(entry);
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            int nextValue = is.read();
            while (-1 != nextValue) {
                byteStream.write(nextValue);
                nextValue = is.read();
            }

            classByte = byteStream.toByteArray();
            result = defineClass(className, classByte, 0, classByte.length, null);
            classes.put(className, result);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

}
