package com.rchenhyy.helloworld._members;

import org.junit.Test;

/**
 * @author chenjiahua.chen
 * @version v1.0.0
 * @since 17/9/16
 */
public class _MethodModifierSpyTest {
    @Test
    public void main() throws Exception {
        // Modifiers:  public volatile
        //  [ synthetic=true  var_args=false bridge=true  ]
        // Modifiers:  public
        //  [ synthetic=false var_args=false bridge=false ]
        _MethodModifierSpy.main("java.lang.String", "compareTo");
        // Modifiers:  public static strictfp
        //  [ synthetic=false var_args=false bridge=false ]
        _MethodModifierSpy.main("java.lang.StrictMath", "toRadians");
        // Modifiers:  public transient
        //  [ synthetic=false var_args=true  bridge=false ]
        _MethodModifierSpy.main("java.lang.Class", "getConstructor");
    }

}