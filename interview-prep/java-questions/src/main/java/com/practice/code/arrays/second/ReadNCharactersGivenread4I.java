/*Given a file and assume that you can only read the
file using a given method read4, implement a method to read n characters.
Method read4:
The API read4 reads four consecutive characters from file, then writes those characters into the buffer array buf4.
The return value is the number of actual characters read.
Note that read4() has its own file pointer, much like FILE *fp in C.
Definition of read4:

    Parameter:  char[] buf4
    Returns:    int

buf4[] is a destination, not a source. The results from read4 will be copied to buf4[].*/
package com.practice.code.arrays.second;

import com.practice.code.runner.CodeRunner;

public class ReadNCharactersGivenread4I implements CodeRunner {
    @Override
    public void run() {

    }

    int read4(char[] buf4) {
        return 4;
    }

    public int read(char[] buf, int n) {
        int copiedChars = 0, readChars = 4;
        char[] buf4 = new char[4];

        while (copiedChars < n && readChars == 4) {
            readChars = read4(buf4);

            for (int i = 0; i < readChars; ++i) {
                if (copiedChars == n)
                    return copiedChars;
                buf[copiedChars] = buf4[i];
                ++copiedChars;
            }
        }
        return copiedChars;
    }
}
