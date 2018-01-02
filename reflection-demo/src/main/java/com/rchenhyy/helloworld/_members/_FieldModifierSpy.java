package com.rchenhyy.helloworld._members;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static java.lang.System.out;

enum Spy {
    BLACK, WHITE
}

public class _FieldModifierSpy {
    volatile int share;
    int instance;

    // anonymous inner class
    class Inner {
        class AA {
            class BB {

            }
        }
    }

    @Test
    public void test() {
        main("com.rchenhyy.helloworld._members._FieldModifierSpy", "volatile");
        main("com.rchenhyy.helloworld._members.Spy", "public");
        // Fields in Class 'com.rchenhyy.helloworld._members._FieldModifierSpy$Inner' containing modifiers: final
        // this$0 [ synthetic=true enum_constant=false ]
        main("com.rchenhyy.helloworld._members._FieldModifierSpy$Inner", "final");
        main("com.rchenhyy.helloworld._members._FieldModifierSpy$Inner$AA", "final");
        main("com.rchenhyy.helloworld._members._FieldModifierSpy$Inner$AA$BB", "final");
        // Fields in Class 'Spy' containing modifiers:  private static final
        // $VALUES  [ synthetic=true  enum_constant=false ]
        main("com.rchenhyy.helloworld._members.Spy", "private", "static", "final");
    }

    /**
     * Notice that some fields are reported even though they are not declared in the original code. This is because the
     * compiler will generate some synthetic fields which are needed during runtime. To test whether a field is
     * synthetic, the example invokes {@link Field#isSynthetic()}.
     * 
     * @param args
     */
    public static void main(String... args) {
        try {
            Class<?> c = Class.forName(args[0]);
            int searchMods = 0x0;
            for (int i = 1; i < args.length; i++) {
                searchMods |= modifierFromString(args[i]);
            }

            Field[] flds = c.getDeclaredFields();
            out.format("Fields in Class '%s' containing modifiers:  %s%n", c.getName(), Modifier.toString(searchMods));
            boolean found = false;
            for (Field f : flds) {
                int foundMods = f.getModifiers();
                // Require all of the requested modifiers to be present
                if ((foundMods & searchMods) == searchMods) {
                    out.format("%-8s [ synthetic=%-5b enum_constant=%-5b ]%n", f.getName(), f.isSynthetic(),
                            f.isEnumConstant());
                    found = true;
                }
            }

            if (!found) {
                out.format("No matching fields%n");
            }

            // production code should handle this exception more gracefully
        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }

    private static int modifierFromString(String s) {
        int m = 0x0;
        if ("public".equals(s))
            m |= Modifier.PUBLIC;
        else if ("protected".equals(s))
            m |= Modifier.PROTECTED;
        else if ("private".equals(s))
            m |= Modifier.PRIVATE;
        else if ("static".equals(s))
            m |= Modifier.STATIC;
        else if ("final".equals(s))
            m |= Modifier.FINAL;
        else if ("transient".equals(s))
            m |= Modifier.TRANSIENT;
        else if ("volatile".equals(s))
            m |= Modifier.VOLATILE;
        return m;
    }
}
