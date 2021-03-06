/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package tests.api.java.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.IllegalFormatException;
import java.util.Locale;

import dalvik.annotation.TestLevel;
import dalvik.annotation.TestTargetClass;
import dalvik.annotation.TestTargetNew;

import tests.support.Support_Locale;

@TestTargetClass(PrintStream.class) 
public class PrintStreamTest extends junit.framework.TestCase {

    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    byte[] ibuf = new byte[4096];
    
    private File testFile = null;
    private String testFilePath = null;

    public String fileString = "Test_All_Tests\nTest_java_io_BufferedInputStream\nTest_java_io_BufferedOutputStream\nTest_java_io_ByteArrayInputStream\nTest_java_io_ByteArrayOutputStream\nTest_java_io_DataInputStream\nTest_java_io_File\nTest_java_io_FileDescriptor\nTest_java_io_FileInputStream\nTest_java_io_FileNotFoundException\nTest_java_io_FileOutputStream\nTest_java_io_FilterInputStream\nTest_java_io_FilterOutputStream\nTest_java_io_InputStream\nTest_java_io_IOException\nTest_java_io_OutputStream\nTest_PrintStream\nTest_java_io_RandomAccessFile\nTest_java_io_SyncFailedException\nTest_java_lang_AbstractMethodError\nTest_java_lang_ArithmeticException\nTest_java_lang_ArrayIndexOutOfBoundsException\nTest_java_lang_ArrayStoreException\nTest_java_lang_Boolean\nTest_java_lang_Byte\nTest_java_lang_Character\nTest_java_lang_Class\nTest_java_lang_ClassCastException\nTest_java_lang_ClassCircularityError\nTest_java_lang_ClassFormatError\nTest_java_lang_ClassLoader\nTest_java_lang_ClassNotFoundException\nTest_java_lang_CloneNotSupportedException\nTest_java_lang_Double\nTest_java_lang_Error\nTest_java_lang_Exception\nTest_java_lang_ExceptionInInitializerError\nTest_java_lang_Float\nTest_java_lang_IllegalAccessError\nTest_java_lang_IllegalAccessException\nTest_java_lang_IllegalArgumentException\nTest_java_lang_IllegalMonitorStateException\nTest_java_lang_IllegalThreadStateException\nTest_java_lang_IncompatibleClassChangeError\nTest_java_lang_IndexOutOfBoundsException\nTest_java_lang_InstantiationError\nTest_java_lang_InstantiationException\nTest_java_lang_Integer\nTest_java_lang_InternalError\nTest_java_lang_InterruptedException\nTest_java_lang_LinkageError\nTest_java_lang_Long\nTest_java_lang_Math\nTest_java_lang_NegativeArraySizeException\nTest_java_lang_NoClassDefFoundError\nTest_java_lang_NoSuchFieldError\nTest_java_lang_NoSuchMethodError\nTest_java_lang_NullPointerException\nTest_java_lang_Number\nTest_java_lang_NumberFormatException\nTest_java_lang_Object\nTest_java_lang_OutOfMemoryError\nTest_java_lang_RuntimeException\nTest_java_lang_SecurityManager\nTest_java_lang_Short\nTest_java_lang_StackOverflowError\nTest_java_lang_String\nTest_java_lang_StringBuffer\nTest_java_lang_StringIndexOutOfBoundsException\nTest_java_lang_System\nTest_java_lang_Thread\nTest_java_lang_ThreadDeath\nTest_java_lang_ThreadGroup\nTest_java_lang_Throwable\nTest_java_lang_UnknownError\nTest_java_lang_UnsatisfiedLinkError\nTest_java_lang_VerifyError\nTest_java_lang_VirtualMachineError\nTest_java_lang_vm_Image\nTest_java_lang_vm_MemorySegment\nTest_java_lang_vm_ROMStoreException\nTest_java_lang_vm_VM\nTest_java_lang_Void\nTest_java_net_BindException\nTest_java_net_ConnectException\nTest_java_net_DatagramPacket\nTest_java_net_DatagramSocket\nTest_java_net_DatagramSocketImpl\nTest_java_net_InetAddress\nTest_java_net_NoRouteToHostException\nTest_java_net_PlainDatagramSocketImpl\nTest_java_net_PlainSocketImpl\nTest_java_net_Socket\nTest_java_net_SocketException\nTest_java_net_SocketImpl\nTest_java_net_SocketInputStream\nTest_java_net_SocketOutputStream\nTest_java_net_UnknownHostException\nTest_java_util_ArrayEnumerator\nTest_java_util_Date\nTest_java_util_EventObject\nTest_java_util_HashEnumerator\nTest_java_util_Hashtable\nTest_java_util_Properties\nTest_java_util_ResourceBundle\nTest_java_util_tm\nTest_java_util_Vector\n";
    
    private static class MockPrintStream extends PrintStream {

        public MockPrintStream(String fileName) throws FileNotFoundException {
            super(fileName);
        }
        
        public MockPrintStream(String fileName, String csn) throws FileNotFoundException, UnsupportedEncodingException {
            super(fileName, csn);
        }
        
        public MockPrintStream(OutputStream os) {
            super(os);
        }
        
        @Override
        public void setError() {
            super.setError();
        }
    }

    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "PrintStream",
        args = {java.io.File.class}
    )
    public void test_Constructor_Ljava_io_File() throws IOException {
        PrintStream tobj;

        tobj = new PrintStream(testFile);
        assertNotNull(tobj);
        tobj.write(1);
        tobj.close();
        assertEquals("output file has wrong length", 1, testFile.length());
        tobj = new PrintStream(testFile);
        assertNotNull(tobj);
        tobj.close();
        assertEquals("output file should be empty", 0, testFile.length());

        File file = new File("/invalidDirectory/Dummy");
        try {
            tobj = new PrintStream(file);
            fail("FileNotFoundException not thrown.");
        } catch (FileNotFoundException e) {
            // expected
        }
    }
    
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "PrintStream",
        args = {java.io.File.class, java.lang.String.class}
    )
    public void test_Constructor_Ljava_io_File_Ljava_lang_String() throws Exception {
        PrintStream tobj;

        tobj = new PrintStream(testFile, "utf-8");
        assertNotNull(tobj);
        tobj.write(1);
        tobj.close();
        assertEquals("output file has wrong length", 1, testFile.length());
        tobj = new PrintStream(testFile, "utf-8");
        assertNotNull(tobj);
        tobj.close();
        assertEquals("output file should be empty", 0, testFile.length());

        File file = new File("/invalidDirectory/Dummy");
        try {
            tobj = new PrintStream(file, "utf-8");
            fail("FileNotFoundException not thrown.");
        } catch (FileNotFoundException e) {
            // expected
        }

        try {
            tobj = new PrintStream(testFile, "invalidEncoding");
            fail("UnsupportedEncodingException not thrown.");
        } catch (UnsupportedEncodingException e) {
            // expected
        }
    }

    /**
     * @tests {@link java.io.PrintStream#PrintStream(String)}
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "PrintStream",
        args = {java.lang.String.class}
    )
    public void test_Constructor_Ljava_lang_String() throws IOException {
        PrintStream tobj;

        tobj = new PrintStream(testFilePath);
        assertNotNull(tobj);
        tobj.write(1);
        tobj.close();
        assertEquals("output file has wrong length", 1, testFile.length());
        tobj = new PrintStream(testFilePath);
        assertNotNull(tobj);
        tobj.close();
        assertEquals("output file should be empty", 0, testFile.length());

        try {
            tobj = new PrintStream("/invalidDirectory/Dummy");
            fail("FileNotFoundException not thrown.");
        } catch (FileNotFoundException e) {
            // expected
        }
    }
    
    /**
     * @tests {@link java.io.PrintStream#PrintStream(String, String)}
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "PrintStream",
        args = {java.lang.String.class, java.lang.String.class}
    )
    public void test_Constructor_Ljava_lang_String_Ljava_lang_String() throws Exception {
        PrintStream tobj;

        tobj = new PrintStream(testFilePath, "utf-8");
        assertNotNull(tobj);
        tobj.write(1);
        tobj.close();
        assertEquals("output file has wrong length", 1, testFile.length());
        tobj = new PrintStream(testFilePath, "utf-8");
        assertNotNull(tobj);
        tobj.close();
        assertEquals("output file should be empty", 0, testFile.length());

        try {
            tobj = new PrintStream("/invalidDirectory/", "utf-8");
            fail("FileNotFoundException not thrown.");
        } catch (FileNotFoundException e) {
            // expected
        }

        try {
            tobj = new PrintStream(testFilePath, "invalidEncoding");
            fail("UnsupportedEncodingException not thrown.");
        } catch (UnsupportedEncodingException e) {
            // expected
        }
    }

    /**
     * @tests java.io.PrintStream#PrintStream(java.io.OutputStream)
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "PrintStream",
        args = {java.io.OutputStream.class}
    )
    public void test_ConstructorLjava_io_OutputStream() throws Exception {
        // Test for method java.io.PrintStream(java.io.OutputStream)
        PrintStream os = new PrintStream(baos);
        os.print(2345.76834720202);
        os.close();

        // regression for HARMONY-1195
        try {
            os = new PrintStream(baos, true, null);
            fail("Should throw NPE");
        } catch (NullPointerException e) {}
    }

    /**
     * @throws FileNotFoundException 
     * @tests java.io.PrintStream#PrintStream(java.io.OutputStream, boolean)
     */
    @TestTargetNew(
        level = TestLevel.SUFFICIENT,
        notes = "Passing FALSE for autoFlush not tested.",
        method = "PrintStream",
        args = {java.io.OutputStream.class, boolean.class}
    )
    public void test_ConstructorLjava_io_OutputStreamZ() throws FileNotFoundException {
        PrintStream tobj;

        tobj = new PrintStream(baos, true);
        tobj.println(2345.76834720202);
        assertTrue("Bytes not written", baos.size() > 0);
        tobj.close();

//        tobj = new PrintStream(bos, false);
//        tobj.println(2345.76834720202);
//        assertEquals("Bytes should not be written, yet", 0, bos.size());
//        tobj.flush();
//        assertTrue("Bytes not written", bos.size() > 0);
//        tobj.close();

//        FileOutputStream fos = new FileOutputStream(testFile);
//
//        tobj = new PrintStream(fos, true);
//        tobj.println(2345.76834720202);
//        assertTrue("Bytes not written", testFile.length() > 0);
//        tobj.close();
//
//        tobj = new PrintStream(fos, false);
//        tobj.println(2345.76834720202);
//        assertTrue("Bytes not written", testFile.length() > 0);
//        tobj.close();
//
    }

    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "PrintStream",
        args = {java.io.OutputStream.class, boolean.class, java.lang.String.class}
    )
    public void test_ConstructorLjava_io_OutputStream_Z_Ljava_lang_String() {
        PrintStream tobj;

        tobj = new PrintStream(baos, true);
        tobj.println(2345.76834720202);
        assertTrue("Bytes not written", baos.size() > 0);
        tobj.close();

        try {
            tobj = new PrintStream(baos, true, "invalidEncoding");
            fail("UnsupportedEncodingException not thrown.");
        } catch (UnsupportedEncodingException e) {
            // expected
        }
    }

    /**
     * @tests java.io.PrintStream#checkError()
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "checkError",
        args = {}
    )
    public void test_checkError() throws Exception {
        // Test for method boolean java.io.PrintStream.checkError()
        PrintStream os = new PrintStream(new OutputStream() {

            public void write(int b) throws IOException {
                throw new IOException();
            }

            public void write(byte[] b, int o, int l) throws IOException {
                throw new IOException();
            }
        });
        os.print(fileString.substring(0, 501));

        assertTrue("Checkerror should return true", os.checkError());
    }

    /**
     * @tests java.io.PrintStream#setError()
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        method = "setError",
        args = {}
    )
    public void test_setError() throws Exception {
        MockPrintStream os = new MockPrintStream(new ByteArrayOutputStream());
        assertFalse("Test 1: Error flag should not be set.", os.checkError());
        os.setError();
        assertTrue("Test 2: Error flag should be set.", os.checkError());
    }
    
    /**
     * @tests java.io.PrintStream#close()
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "close",
        args = {}
    )
    public void test_close() throws Exception {
        // Test for method void java.io.PrintStream.close()
        PrintStream os = new PrintStream(baos);
        os.close();
        baos.close();
    }

    /**
     * @tests java.io.PrintStream#flush()
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "flush",
        args = {}
    )
    public void test_flush() throws Exception {
        // Test for method void java.io.PrintStream.flush()
        PrintStream os = new PrintStream(baos);
        os.print(fileString.substring(0, 501));
        os.flush();
        assertEquals("Bytes not written after flush", 501, baos.size());
        baos.close();
        os.close();
    }

    /**
     * @tests java.io.PrintStream#print(char[])
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "print",
        args = {char[].class}
    )
    public void test_print$C() {
        // Test for method void java.io.PrintStream.print(char [])
        PrintStream os = new PrintStream(baos, true);
        try {
            os.print((char[]) null);
            fail("NPE expected");
        } catch (NullPointerException ok) {}

        os = new PrintStream(baos, true);
        char[] sc = new char[4000];
        fileString.getChars(0, fileString.length(), sc, 0);
        os.print(sc);
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        os.close();

        byte[] rbytes = new byte[4000];
        bis.read(rbytes, 0, fileString.length());
        assertEquals("Incorrect char[] written", fileString, new String(rbytes,
                0, fileString.length()));
    }

    /**
     * @tests java.io.PrintStream#print(char)
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "print",
        args = {char.class}
    )
    public void test_printC() {
        // Test for method void java.io.PrintStream.print(char)
        PrintStream os = new PrintStream(baos, true);
        os.print('t');
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        assertEquals("Incorrect char written", 't', bis.read());
    }

    /**
     * @tests java.io.PrintStream#print(double)
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "print",
        args = {double.class}
    )
    public void test_printD() {
        // Test for method void java.io.PrintStream.print(double)
        byte[] rbuf = new byte[100];
        PrintStream os = new PrintStream(baos, true);
        os.print(2345.76834720202);
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        bis.read(rbuf, 0, 16);
        assertEquals("Incorrect double written", "2345.76834720202",
                new String(rbuf, 0, 16));
    }

    /**
     * @tests java.io.PrintStream#print(float)
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "print",
        args = {float.class}
    )
    public void test_printF() {
        // Test for method void java.io.PrintStream.print(float)
        PrintStream os = new PrintStream(baos, true);
        byte rbuf[] = new byte[10];
        os.print(29.08764f);
        os.flush();
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        bis.read(rbuf, 0, 8);
        assertEquals("Incorrect float written", "29.08764", new String(rbuf, 0,
                8));

    }

    /**
     * @tests java.io.PrintStream#print(int)
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "print",
        args = {int.class}
    )
    public void test_printI() {
        // Test for method void java.io.PrintStream.print(int)
        PrintStream os = new PrintStream(baos, true);
        os.print(768347202);
        byte[] rbuf = new byte[18];
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        bis.read(rbuf, 0, 9);
        assertEquals("Incorrect int written", "768347202", new String(rbuf, 0,
                9));
    }

    /**
     * @tests java.io.PrintStream#print(long)
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "print",
        args = {long.class}
    )
    public void test_printJ() {
        // Test for method void java.io.PrintStream.print(long)
        byte[] rbuf = new byte[100];
        PrintStream os = new PrintStream(baos, true);
        os.print(9875645283333L);
        os.close();
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        bis.read(rbuf, 0, 13);
        assertEquals("Incorrect long written", "9875645283333", new String(
                rbuf, 0, 13));
    }

    /**
     * @tests java.io.PrintStream#print(java.lang.Object)
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "print",
        args = {java.lang.Object.class}
    )
    public void test_printLjava_lang_Object() throws Exception {
        // Test for method void java.io.PrintStream.print(java.lang.Object)
        PrintStream os = new PrintStream(baos, true);
        os.print((Object) null);
        os.flush();
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        byte[] nullbytes = new byte[4];
        bis.read(nullbytes, 0, 4);
        assertEquals("null should be written", "null", new String(nullbytes, 0,
                4));

        bis.close();
        baos.close();
        os.close();

        ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
        os = new PrintStream(bos1, true);
        os.print(new java.util.Vector());
        bis = new ByteArrayInputStream(bos1.toByteArray());
        byte[] rbytes = new byte[2];
        bis.read(rbytes, 0, 2);
        assertEquals("Incorrect Object written", "[]", new String(rbytes, 0, 2));
    }

    /**
     * @tests java.io.PrintStream#print(java.lang.String)
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "print",
        args = {java.lang.String.class}
    )
    public void test_printLjava_lang_String() throws Exception {
        // Test for method void java.io.PrintStream.print(java.lang.String)
        PrintStream os = new PrintStream(baos, true);
        os.print((String) null);
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        byte[] nullbytes = new byte[4];
        bis.read(nullbytes, 0, 4);
        assertEquals("null should be written", "null", new String(nullbytes, 0,
                4));

        bis.close();
        baos.close();
        os.close();

        ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
        os = new PrintStream(bos1, true);
        os.print("Hello World");
        bis = new ByteArrayInputStream(bos1.toByteArray());
        byte rbytes[] = new byte[100];
        bis.read(rbytes, 0, 11);
        assertEquals("Incorrect string written", "Hello World", new String(
                rbytes, 0, 11));
    }

    /**
     * @tests java.io.PrintStream#print(boolean)
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "print",
        args = {boolean.class}
    )
    public void test_printZ() throws Exception {
        // Test for method void java.io.PrintStream.print(boolean)
        PrintStream os = new PrintStream(baos, true);
        os.print(true);
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(baos
                .toByteArray()));

        assertTrue("Incorrect boolean written", dis.readBoolean());
    }

    /**
     * @tests java.io.PrintStream#println()
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "println",
        args = {}
    )
    public void test_println() {
        // Test for method void java.io.PrintStream.println()
        char c;
        PrintStream os = new PrintStream(baos, true);
        os.println("");
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        assertTrue("Newline not written", (c = (char) bis.read()) == '\r'
                || c == '\n');
    }

    /**
     * @tests java.io.PrintStream#println(char[])
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "println",
        args = {char[].class}
    )
    public void test_println$C() {
        // Test for method void java.io.PrintStream.println(char [])
        PrintStream os = new PrintStream(baos, true);
        char[] sc = new char[4000];
        fileString.getChars(0, fileString.length(), sc, 0);
        os.println(sc);
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        byte[] rbytes = new byte[4000];
        bis.read(rbytes, 0, fileString.length());
        assertEquals("Incorrect char[] written", fileString, new String(rbytes,
                0, fileString.length()));

        // In this particular test method, the end of data is not immediately
        // followed by newLine separator in the reading buffer, instead its
        // followed by zeros. The newline is written as the last entry
        // in the inputStream buffer. Therefore, we must keep reading until we
        // hit a new line.
        int r;
        boolean newline = false;
        while ((r = bis.read()) != -1) {
            if (r == '\r' || r == '\n')
                newline = true;
        }
        assertTrue("Newline not written", newline);
    }

    /**
     * @tests java.io.PrintStream#println(char)
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "println",
        args = {char.class}
    )
    public void test_printlnC() {
        // Test for method void java.io.PrintStream.println(char)
        int c;
        PrintStream os = new PrintStream(baos, true);
        os.println('t');
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        assertEquals("Incorrect char written", 't', bis.read());
        assertTrue("Newline not written", (c = bis.read()) == '\r' || c == '\n');
    }

    /**
     * @tests java.io.PrintStream#println(double)
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "println",
        args = {double.class}
    )
    public void test_printlnD() {
        // Test for method void java.io.PrintStream.println(double)
        int c;
        PrintStream os = new PrintStream(baos, true);
        os.println(2345.76834720202);
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        byte[] rbuf = new byte[100];
        bis.read(rbuf, 0, 16);
        assertEquals("Incorrect double written", "2345.76834720202",
                new String(rbuf, 0, 16));
        assertTrue("Newline not written", (c = bis.read()) == '\r' || c == '\n');
    }

    /**
     * @tests java.io.PrintStream#println(float)
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "println",
        args = {float.class}
    )
    public void test_printlnF() {
        // Test for method void java.io.PrintStream.println(float)
        int c;
        byte[] rbuf = new byte[100];
        PrintStream os = new PrintStream(baos, true);
        os.println(29.08764f);
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        bis.read(rbuf, 0, 8);
        assertEquals("Incorrect float written", "29.08764", new String(rbuf, 0,
                8));
        assertTrue("Newline not written", (c = bis.read()) == '\r' || c == '\n');
    }

    /**
     * @tests java.io.PrintStream#println(int)
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "println",
        args = {int.class}
    )
    public void test_printlnI() {
        // Test for method void java.io.PrintStream.println(int)
        int c;
        PrintStream os = new PrintStream(baos, true);
        os.println(768347202);
        byte[] rbuf = new byte[100];
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        bis.read(rbuf, 0, 9);
        assertEquals("Incorrect int written", "768347202", new String(rbuf, 0,
                9));
        assertTrue("Newline not written", (c = bis.read()) == '\r' || c == '\n');
    }

    /**
     * @tests java.io.PrintStream#println(long)
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "println",
        args = {long.class}
    )
    public void test_printlnJ() {
        // Test for method void java.io.PrintStream.println(long)
        int c;
        PrintStream os = new PrintStream(baos, true);
        os.println(9875645283333L);
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        byte[] rbuf = new byte[100];
        bis.read(rbuf, 0, 13);
        assertEquals("Incorrect long written", "9875645283333", new String(
                rbuf, 0, 13));
        assertTrue("Newline not written", (c = bis.read()) == '\r' || c == '\n');
    }

    /**
     * @tests java.io.PrintStream#println(java.lang.Object)
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "println",
        args = {java.lang.Object.class}
    )
    public void test_printlnLjava_lang_Object() {
        // Test for method void java.io.PrintStream.println(java.lang.Object)
        char c;
        PrintStream os = new PrintStream(baos, true);
        os.println(new java.util.Vector());
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        byte[] rbytes = new byte[2];
        bis.read(rbytes, 0, 2);
        assertEquals("Incorrect Vector written", "[]", new String(rbytes, 0, 2));
        assertTrue("Newline not written", (c = (char) bis.read()) == '\r'
                || c == '\n');
    }

    /**
     * @tests java.io.PrintStream#println(java.lang.String)
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "println",
        args = {java.lang.String.class}
    )
    public void test_printlnLjava_lang_String() {
        // Test for method void java.io.PrintStream.println(java.lang.String)
        char c;
        PrintStream os = new PrintStream(baos, true);
        os.println("Hello World");
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        byte rbytes[] = new byte[100];
        bis.read(rbytes, 0, 11);
        assertEquals("Incorrect string written", "Hello World", new String(
                rbytes, 0, 11));
        assertTrue("Newline not written", (c = (char) bis.read()) == '\r'
                || c == '\n');
    }

    /**
     * @tests java.io.PrintStream#println(boolean)
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "println",
        args = {boolean.class}
    )
    public void test_printlnZ() {
        // Test for method void java.io.PrintStream.println(boolean)
        int c;
        PrintStream os = new PrintStream(baos, true);
        os.println(true);
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        byte[] rbuf = new byte[100];
        bis.read(rbuf, 0, 4);
        assertEquals("Incorrect boolean written", "true",
                new String(rbuf, 0, 4));
        assertTrue("Newline not written", (c = bis.read()) == '\r' || c == '\n');
    }

    /**
     * @tests java.io.PrintStream#write(byte[], int, int)
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "write",
        args = {byte[].class, int.class, int.class}
    )
    public void test_write$BII() {
        // Test for method void java.io.PrintStream.write(byte [], int, int)
        PrintStream os = new PrintStream(baos, true);
        os.write(fileString.getBytes(), 0, fileString.length());
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        byte rbytes[] = new byte[4000];
        bis.read(rbytes, 0, fileString.length());
        assertTrue("Incorrect bytes written", new String(rbytes, 0, fileString
                .length()).equals(fileString));
        
        try {
            os.write(rbytes, -1, 1);
            fail("IndexOutOfBoundsException should have been thrown.");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        
        try {
            os.write(rbytes, 0, -1);
            fail("IndexOutOfBoundsException should have been thrown.");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        
        try {
            os.write(rbytes, 1, rbytes.length);
            fail("IndexOutOfBoundsException should have been thrown.");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    /**
     * @tests java.io.PrintStream#write(int)
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "write",
        args = {int.class}
    )
    public void test_writeI() {
        // Test for method void java.io.PrintStream.write(int)
        PrintStream os = new PrintStream(baos, true);
        os.write('t');
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        assertEquals("Incorrect char written", 't', bis.read());
    }

    /**
     * @tests java.io.PrintStream#append(char)
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "append",
        args = {char.class}
    )
    public void test_appendChar() throws IOException {
        char testChar = ' ';
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        printStream.append(testChar);
        printStream.flush();
        assertEquals(String.valueOf(testChar), out.toString());
        printStream.close();
    }

    /**
     * @tests java.io.PrintStream#append(CharSequence)
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "append",
        args = {java.lang.CharSequence.class}
    )
    public void test_appendCharSequence() {
        String testString = "My Test String";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        printStream.append(testString);
        printStream.flush();
        assertEquals(testString, out.toString());
        printStream.close();
    }

    /**
     * @tests java.io.PrintStream#append(CharSequence, int, int)
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "append",
        args = {java.lang.CharSequence.class, int.class, int.class}
    )
    public void test_appendCharSequenceIntInt() {
        String testString = "My Test String";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        printStream.append(testString, 1, 3);
        printStream.flush();
        assertEquals(testString.substring(1, 3), out.toString());
        try {
            printStream.append(testString, 4, 100);
            fail("IndexOutOfBoundsException not thrown");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        try {
            printStream.append(testString, 100, 1);
            fail("IndexOutOfBoundsException not thrown");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        printStream.close();
    }

    /**
     * @tests java.io.PrintStream#format(java.lang.String, java.lang.Object...)
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "format",
        args = {java.lang.String.class, java.lang.Object[].class}
    )
    public void test_formatLjava_lang_String$Ljava_lang_Object() {
        PrintStream tobj;

        tobj = new PrintStream(baos, false);
        tobj.format("%s %s", "Hello", "World");
        tobj.flush();
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        byte[] rbytes = new byte[11];
        bis.read(rbytes, 0, rbytes.length);
        assertEquals("Wrote incorrect string", "Hello World",
                new String(rbytes));

        baos.reset();
        tobj = new PrintStream(baos);
        tobj.format("%1$.3G, %1$.5f, 0%2$xx", 12345.678, 123456);
        tobj.flush();
        assertEquals("Wrong output!", "1.23E+04, 12345.67800, 01e240x", new String(baos.toByteArray()));
        tobj.close();

        baos.reset();
        tobj = new PrintStream(baos);
        try {
            tobj.format("%1$.3G, %1$x", 12345.678);
            fail("IllegalFormatException not thrown");
        } catch (IllegalFormatException e) {
            // expected
        }

        try {
            tobj.format("%s %q", "Hello", "World");
            fail("IllegalFormatException not thrown");
        } catch (IllegalFormatException e) {
            // expected
        }

        try {
            tobj.format("%s %s", "Hello");
            fail("IllegalFormatException not thrown");
        } catch (IllegalFormatException e) {
            // expected
        }
    }

    /**
     * @tests java.io.PrintStream#format(java.util.Locale, java.lang.String,
     *        java.lang.Object...)
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "format",
        args = {java.util.Locale.class, java.lang.String.class, java.lang.Object[].class}
    )
    public void test_formatLjava_util_Locale_Ljava_lang_String_$Ljava_lang_Object() {
        Locale[] requiredLocales = {Locale.US, Locale.GERMANY};
        if (!Support_Locale.areLocalesAvailable(requiredLocales)) {
            // locale dependent test, bug 1943269
            return;
        }
        PrintStream tobj;

        tobj = new PrintStream(baos, false);
        tobj.format(Locale.US, "%s %s", "Hello", "World");
        tobj.flush();
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        byte[] rbytes = new byte[11];
        bis.read(rbytes, 0, rbytes.length);
        assertEquals("Wrote incorrect string", "Hello World",
                new String(rbytes));

        baos.reset();
        tobj = new PrintStream(baos);
        tobj.format(Locale.GERMANY, "%1$.3G; %1$.5f; 0%2$xx", 12345.678, 123456);
        tobj.flush();
        assertEquals("Wrong output!", "1,23E+04; 12345,67800; 01e240x", new String(baos.toByteArray()));
        tobj.close();

        baos.reset();
        tobj = new PrintStream(baos);
        tobj.format(Locale.US, "%1$.3G, %1$.5f, 0%2$xx", 12345.678, 123456);
        tobj.flush();
        assertEquals("Wrong output!", "1.23E+04, 12345.67800, 01e240x", new String(baos.toByteArray()));
        tobj.close();

        baos.reset();
        tobj = new PrintStream(baos);
        try {
            tobj.format(Locale.US, "%1$.3G, %1$x", 12345.678);
            fail("IllegalFormatException not thrown");
        } catch (IllegalFormatException e) {
            // expected
        }

        try {
            tobj.format(Locale.US, "%s %q", "Hello", "World");
            fail("IllegalFormatException not thrown");
        } catch (IllegalFormatException e) {
            // expected
        }

        try {
            tobj.format(Locale.US, "%s %s", "Hello");
            fail("IllegalFormatException not thrown");
        } catch (IllegalFormatException e) {
            // expected
        }
    }

    /**
     * @tests java.io.PrintStream#printf(java.lang.String, java.lang.Object...)
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "printf",
        args = {java.lang.String.class, java.lang.Object[].class}
    )
    public void test_printfLjava_lang_String$Ljava_lang_Object() {
        PrintStream tobj;

        tobj = new PrintStream(baos, false);
        tobj.printf("%s %s", "Hello", "World");
        tobj.flush();
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        byte[] rbytes = new byte[11];
        bis.read(rbytes, 0, rbytes.length);
        assertEquals("Wrote incorrect string", "Hello World",
                new String(rbytes));

        baos.reset();
        tobj = new PrintStream(baos);
        tobj.printf("%1$.3G, %1$.5f, 0%2$xx", 12345.678, 123456);
        tobj.flush();
        assertEquals("Wrong output!", "1.23E+04, 12345.67800, 01e240x", new String(baos.toByteArray()));
        tobj.close();

        baos.reset();
        tobj = new PrintStream(baos);
        try {
            tobj.printf("%1$.3G, %1$x", 12345.678);
            fail("IllegalFormatException not thrown");
        } catch (IllegalFormatException e) {
            // expected
        }

        try {
            tobj.printf("%s %q", "Hello", "World");
            fail("IllegalFormatException not thrown");
        } catch (IllegalFormatException e) {
            // expected
        }

        try {
            tobj.printf("%s %s", "Hello");
            fail("IllegalFormatException not thrown");
        } catch (IllegalFormatException e) {
            // expected
        }
    }

    /**
     * @tests java.io.PrintStream#printf(java.util.Locale, java.lang.String,
     *        java.lang.Object...)
     */
    @TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "printf",
        args = {java.util.Locale.class, java.lang.String.class, java.lang.Object[].class}
    )
    public void test_printfLjava_util_Locale_Ljava_lang_String_$Ljava_lang_Object() {
        Locale[] requiredLocales = {Locale.US, Locale.GERMANY};
        if (!Support_Locale.areLocalesAvailable(requiredLocales)) {
            // locale dependent test, bug 1943269
            return;
        }
        PrintStream tobj;

        tobj = new PrintStream(baos, false);
        tobj.printf(Locale.US, "%s %s", "Hello", "World");
        tobj.flush();
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        byte[] rbytes = new byte[11];
        bis.read(rbytes, 0, rbytes.length);
        assertEquals("Wrote incorrect string", "Hello World",
                new String(rbytes));

        baos.reset();
        tobj = new PrintStream(baos);
        tobj.printf(Locale.GERMANY, "%1$.3G; %1$.5f; 0%2$xx", 12345.678, 123456);
        tobj.flush();
        assertEquals("Wrong output!", "1,23E+04; 12345,67800; 01e240x", new String(baos.toByteArray()));
        tobj.close();

        baos.reset();
        tobj = new PrintStream(baos);
        tobj.printf(Locale.US, "%1$.3G, %1$.5f, 0%2$xx", 12345.678, 123456);
        tobj.flush();
        assertEquals("Wrong output!", "1.23E+04, 12345.67800, 01e240x", new String(baos.toByteArray()));
        tobj.close();

        baos.reset();
        tobj = new PrintStream(baos);
        try {
            tobj.printf(Locale.US, "%1$.3G, %1$x", 12345.678);
            fail("IllegalFormatException not thrown");
        } catch (IllegalFormatException e) {
            // expected
        }

        try {
            tobj.printf(Locale.US, "%s %q", "Hello", "World");
            fail("IllegalFormatException not thrown");
        } catch (IllegalFormatException e) {
            // expected
        }

        try {
            tobj.printf(Locale.US, "%s %s", "Hello");
            fail("IllegalFormatException not thrown");
        } catch (IllegalFormatException e) {
            // expected
        }
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        testFile = File.createTempFile("test", null);
        testFilePath = testFile.getAbsolutePath();
    }

    @Override
    protected void tearDown() throws Exception {
        testFile.delete();
        testFile = null;
        testFilePath = null;
        super.tearDown();
    }
    
    
}
