package org.extendj;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import org.chocosolver.solver.Model;

import org.extendj.ast.Frontend;
import org.extendj.ast.Program;
import org.extendj.ast.BatakjavaFrontend;

public class BatakjavaOverviewWriter extends BatakjavaFrontend {
  public static void main(String[] args) {
    Model model;
    int exitCode = (new BatakjavaOverviewWriter()).run(args);
    if (exitCode != 0) {
      System.exit(exitCode);
    }

  }

  public BatakjavaOverviewWriter() {
    super("Batakjava Overview Writer", ExtendJVersion.getVersion());
  }

  /** @deprecated */
  @Deprecated
  public static boolean compile(String[] args) {
    return 0 == (new BatakjavaOverviewWriter()).run(args);
  }

  public int run(String[] args) {
    return this.run(args, Program.defaultBytecodeReader(), Program.defaultJavaParser());
  }

}
