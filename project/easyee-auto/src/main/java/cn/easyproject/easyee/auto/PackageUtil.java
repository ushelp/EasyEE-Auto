package cn.easyproject.easyee.auto;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * EasyAuto Package Util
 * @author Ray
 * @author inthinkcolor@gmail.com
 * @author easyproject.cn
 * @since 1.0.0
 */
public class PackageUtil {
    /**

     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.

     *

     * @param packageName The base package

     * @return The classes

     * @throws ClassNotFoundException

     * @throws IOException

     */

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Class> getClasses(String packageName)
            throws ClassNotFoundException, IOException {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        assert classLoader != null;

        String path = packageName.replace('.', '/');

        Enumeration<URL> resources = classLoader.getResources(path);

        List<File> dirs = new ArrayList<File>();

        while (resources.hasMoreElements()) {

            URL resource = resources.nextElement();

            dirs.add(new File(resource.getFile()));

        }

        ArrayList<Class> classes = new ArrayList<Class>();

        for (File directory : dirs) {

            classes.addAll(findClasses(directory, packageName));

        }

        return classes;

    }

    /**

     * Recursive method used to find all classes in a given directory and subdirs.

     *

     * @param directory   The base directory

     * @param packageName The package name for classes found inside the base directory

     * @return The classes

     * @throws ClassNotFoundException

     */

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private static List findClasses(File directory, String packageName) throws ClassNotFoundException {

        List classes = new ArrayList();

        if (!directory.exists()) {

            return classes;

        }

        File[] files = directory.listFiles();

        for (File file : files) {

            if (file.isDirectory()) {

                assert !file.getName().contains(".");

                classes.addAll(findClasses(file, packageName + "." + file.getName()));

            } else if (file.getName().endsWith(".class")) {

                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));

            }

        }

        return classes;

    }
}
