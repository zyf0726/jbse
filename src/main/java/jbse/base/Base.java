package jbse.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessControlContext;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Properties;

import jbse.algo.meta.Algo_JBSE_BASE_CLINIT;
import jbse.meta.annotations.MetaOverriddenBy;
import sun.misc.Unsafe;

/**
 * Some base-level overriding implementations of methods. 
 * 
 * @author Pietro Braione
 *
 */
public final class Base {
    //Properties to be set metacircularly
    private static final String OS_NAME                 = null;
    private static final String OS_VERSION              = null;
    private static final String OS_ARCH                 = null;
    private static final String FILE_SEPARATOR          = null;
    private static final String PATH_SEPARATOR          = null;
    private static final String LINE_SEPARATOR          = null;
    private static final String USER_LANGUAGE           = null;
    private static final String USER_SCRIPT             = null;
    private static final String USER_COUNTRY            = null;
    private static final String USER_VARIANT            = null;
    private static final String FILE_ENCODING           = null;
    private static final String SUN_JNU_ENCODING        = null;
    private static final String SUN_STDOUT_ENCODING     = null;
    private static final String SUN_STDERR_ENCODING     = null;
    private static final String SUN_IO_UNICODE_ENCODING = null;
    private static final String SUN_CPU_ISALIST         = null;
    private static final String SUN_CPU_ENDIAN          = null;
    private static final String HTTP_PROXYHOST          = null;
    private static final String HTTP_PROXYPORT          = null;
    private static final String HTTPS_PROXYHOST         = null;
    private static final String HTTPS_PROXYPORT         = null;
    private static final String FTP_PROXYHOST           = null;
    private static final String FTP_PROXYPORT           = null;
    private static final String SOCKSPROXYHOST          = null;
    private static final String SOCKSPROXYPORT          = null;
    private static final String GOPHERPROXYSET          = null;
    private static final String GOPHERPROXYHOST         = null;
    private static final String GOPHERPROXYPORT         = null;
    private static final String HTTP_NONPROXYHOSTS      = null;
    private static final String FTP_NONPROXYHOSTS       = null;
    private static final String SOCKSNONPROXYHOSTS      = null;

    static {
        clinit();
    }

    /**
     * Sets the {@code private static final String} fields
     * of this class to the values (if exist) of the 
     * corresponding properties at the meta-level.
     */
    @MetaOverriddenBy(Algo_JBSE_BASE_CLINIT.class)
    private static native void clinit();
    
    /**
     * Overriding implementation of {@link java.security.AccessController#doPrivileged(PrivilegedExceptionAction)}.
     * @see java.security.AccessController#doPrivileged(PrivilegedExceptionAction)
     */
    private static Object base_JAVA_ACCESSCONTROLLER_DOPRIVILEGED_EXCEPTION(PrivilegedExceptionAction<?> action)
    throws PrivilegedActionException {
        //since JBSE does not enforce access control we just execute the action
        try {
            return action.run();
        } catch (RuntimeException e) {
            throw e; //runtime exceptions propagate
        } catch (Exception e) {
            throw new PrivilegedActionException(e); //not explicitly told, but this is the only sensible behavior
        }
    }

    /**
     * Overriding implementation of {@link java.security.AccessController#doPrivileged(PrivilegedAction)}.
     * @see java.security.AccessController#doPrivileged(PrivilegedAction)
     */
    private static Object base_JAVA_ACCESSCONTROLLER_DOPRIVILEGED_NOEXCEPTION(PrivilegedAction<?> action)
    throws PrivilegedActionException {
        //since JBSE does not enforce access control we just execute the action
        return action.run();
    }

    /**
     * Overriding implementation of {@link java.security.AccessController#getStackAccessControlContext()}.
     * @see java.security.AccessController#getStackAccessControlContext()
     */
    private static AccessControlContext base_JAVA_ACCESSCONTROLLER_GETSTACKACCESSCONTROLCONTEXT() {
        //JBSE does not (yet) check access control, so a dummy null context is returned signifying
        //privileged access (or so it seems).
        return null;
    }
    
    /**
     * Overriding implementation of {@link java.util.concurrent.atomic.AtomicLong#VMSupportsCS8()}.
     * @see java.util.concurrent.atomic.AtomicLong#VMSupportsCS8()
     */
    private static boolean base_JAVA_ATOMICLONG_VMSUPPORTSCS8() {
        //JBSE trivially supports atomic compare-and-swap for
        //all data types
        return true;
    }

    /**
     * Overriding implementation of {@link java.lang.Class#desiredAssertionStatus0(Class)}.
     * @see java.lang.Class#desiredAssertionStatus0(Class)
     */
    private static final boolean base_JAVA_CLASS_DESIREDASSERTIONSTATUS0(Class<?> clazz) {
        return false; //no assertions, sorry
        //TODO should we give a way to control the assertion status, possibly handling Java assertions as jbse assertions?
    }

    /**
     * Puts a key-value pair in a {@link Properties} object, 
     * if the value is not null, otherwise does nothing.
     * 
     * @param p the {@link Properties}.
     * @param key a {@link String}, the key.
     * @param value a {@link String}, the value.
     */
    private static final void putSafe(Properties p, String key, String value) {
        if (value != null) {
            p.put(key, value);
        }
    }
    
    /**
     * Overriding implementation of {@link java.lang.System#initProperties(Properties)}.
     * @see java.lang.System#initProperties(Properties)
     */
    private static final Properties base_JAVA_SYSTEM_INITPROPERTIES(Properties p) {
        //properties taken from openjdk 8, jdk project, file src/share/native/java/lang/System.c
        putSafe(p, "java.specification.version", "1.8");
        putSafe(p, "java.specification.name",    "Java Platform API Specification");
        putSafe(p, "java.specification.vendor",  "Oracle Corporation");
        putSafe(p, "java.version",               "1.8.0_144");
        putSafe(p, "java.vendor",                "JBSE project");
        putSafe(p, "java.vendor.url",            "http://pietrobraione.github.io/jbse/");
        putSafe(p, "java.vendor.url.bug",        "https://github.com/pietrobraione/jbse/issues");
        putSafe(p, "java.class.version",         "52.0");
        putSafe(p, "os.name",                    OS_NAME);
        putSafe(p, "os.version",                 OS_VERSION);
        putSafe(p, "os.arch",                    OS_ARCH);
        putSafe(p, "file.separator",             FILE_SEPARATOR);
        putSafe(p, "path.separator",             PATH_SEPARATOR);
        putSafe(p, "line.separator",             LINE_SEPARATOR);
        putSafe(p, "user.language",              USER_LANGUAGE);
        putSafe(p, "user.script",                USER_SCRIPT);
        putSafe(p, "user.country",               USER_COUNTRY);
        putSafe(p, "user.variant",               USER_VARIANT);
        putSafe(p, "file.encoding",              FILE_ENCODING);
        putSafe(p, "sun.jnu.encoding",           SUN_JNU_ENCODING);
        putSafe(p, "sun.stdout.encoding",        SUN_STDOUT_ENCODING);
        putSafe(p, "sun.stderr.encoding",        SUN_STDERR_ENCODING);
        putSafe(p, "file.encoding.pkg",          "sun.io");
        putSafe(p, "sun.io.unicode.encoding",    SUN_IO_UNICODE_ENCODING);
        putSafe(p, "sun.cpu.isalist",            SUN_CPU_ISALIST);
        putSafe(p, "sun.cpu.endian",             SUN_CPU_ENDIAN);
        putSafe(p, "http.proxyHost",             HTTP_PROXYHOST);
        putSafe(p, "http.proxyPort",             HTTP_PROXYPORT);
        putSafe(p, "https.proxyHost",            HTTPS_PROXYHOST);
        putSafe(p, "https.proxyPort",            HTTPS_PROXYPORT);
        putSafe(p, "ftp.proxyHost",              FTP_PROXYHOST);
        putSafe(p, "ftp.proxyPort",              FTP_PROXYPORT);
        putSafe(p, "socksProxyHost",             SOCKSPROXYHOST);
        putSafe(p, "socksProxyPort",             SOCKSPROXYPORT);
        putSafe(p, "gopherProxySet",             GOPHERPROXYSET);
        putSafe(p, "gopherProxyHost",            GOPHERPROXYHOST);
        putSafe(p, "gopherProxyPort",            GOPHERPROXYPORT);
        putSafe(p, "http.nonProxyHosts",         HTTP_NONPROXYHOSTS);
        putSafe(p, "ftp.nonProxyHosts",          FTP_NONPROXYHOSTS);
        putSafe(p, "socksNonProxyHosts",         SOCKSNONPROXYHOSTS);
        //TODO more properties?
        return p;
    }

    /**
     * Overriding implementation of {@link java.lang.Thread#isAlive()}.
     * @see java.lang.Thread#isAlive()
     */
    private static boolean base_JAVA_THREAD_ISALIVE(Thread _this) {
        //in JBSE there is only one thread alive, the current thread
        return (_this == Thread.currentThread());
    }
    
    private static void foo() throws Exception { }
    
    /**
     * Helper method for {@link sun.reflect.NativeConstructorAccessorImpl#newInstanceFromConstructor(Constructor, Object[])}.
     * Just boxes the exceptions that are raised by the execution of a constructor 
     * into an {@link InvocationTargetException} and rethrows them
     */
    private static void boxInvocationTargetException() 
    throws InvocationTargetException {
        //the implementation of this method just boxes exceptions and rethrows them
        try {
            foo(); //does nothing, it's just to force the compiler to generate the catch block
        } catch (Exception e) {
            throw new InvocationTargetException(e);
        }
    }
    
    /**
     * Overriding implementation of {@link jbse.meta.Analysis#isRunByJBSE()}.
     * @see jbse.meta.Analysis#isRunByJBSE()
     */
    private static boolean base_JBSE_ANALYSIS_ISRUNBYJBSE() {
        return true;
    }

    /**
     * Overriding implementation of {@link sun.misc.Unsafe#addressSize()}.
     * @see sun.misc.Unsafe#addressSize()
     */
    private static int base_SUN_UNSAFE_ADDRESSSIZE(Unsafe _this) {
        //JBSE offers no raw access to its data structures, so we return a dummy value
        return 8; //can be either 4 or 8, we choose 8 
    }

    /**
     * Overriding implementation of {@link sun.misc.Unsafe#arrayBaseOffset(Class)}.
     * @see sun.misc.Unsafe#arrayBaseOffset(Class)
     */
    private static int base_SUN_UNSAFE_ARRAYBASEOFFSET(Unsafe _this, Class<?> arrayClass) {
        //JBSE raw array offsets are plain array indices, so base is zero
        return 0; 
    }

    /**
     * Overriding implementation of {@link sun.misc.Unsafe#arrayIndexScale(Class)}.
     * @see sun.misc.Unsafe#arrayIndexScale(Class)
     */
    private static int base_SUN_UNSAFE_ARRAYINDEXSCALE(Unsafe _this, Class<?> arrayClass) {
        //JBSE raw array offsets are plain array indices, so scale is one
        return 1; 
    }

    private Base() {
        //do not instantiate!
        throw new AssertionError();
    }
}
