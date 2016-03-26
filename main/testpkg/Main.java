/*
 * This is free and unencumbered software released into the public domain.
 *
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 *
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * For more information, please refer to <http://unlicense.org/>
 */
package testpkg;

import foo.*;
import bar.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.jar.*;

public class Main {
    public static void main(String[] args) throws Exception {
        (new Main()).doit();
    }

    public void doit() throws Exception {
        URL locurl = this.getClass().getProtectionDomain().getCodeSource().getLocation();
        System.out.println("Main : " + locurl.getProtocol());
        System.out.println("Main : " + locurl.getFile());
        File locfile = new File(locurl.getFile());
        if (locfile.isDirectory()) {
            printFileList(locfile);
        } else {
            dumpJar(locurl.getFile());
        }
        (new Foo()).printMyLocation();
        (new Bar()).printMyLocation();
    }

    void printFileList(File root) {
        File[] files = root.listFiles();
        if (null == files) {
            return;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                System.out.println("[D]:" + f.getAbsolutePath());
                printFileList(new File(f.getAbsolutePath()));
            } else {
                System.out.println("[F]:" + f.getAbsolutePath());
            }
        }
    }

    void dumpJar(String jarPath) throws Exception {
        JarFile jar = new JarFile(jarPath);
        for (Enumeration<JarEntry> enumjar = jar.entries(); enumjar.hasMoreElements();) {
            JarEntry ent = enumjar.nextElement();
            if (ent.isDirectory()) {
                System.out.println("[D]:" + ent.getName());
            } else {
                System.out.println("[F]:" + ent.getName());
            }
        }
    }
}
