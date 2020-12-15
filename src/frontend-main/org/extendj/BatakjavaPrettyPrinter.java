package org.extendj;

import org.extendj.ast.Program;
import org.extendj.ast.CompilationUnit;
import org.extendj.ast.BatakjavaFrontend;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import org.chocosolver.solver.Model;


public class BatakjavaPrettyPrinter extends BatakjavaFrontend {
  public static void main(String[] args) {
    BatakjavaPrettyPrinter printer = new BatakjavaPrettyPrinter();
    int exitCode = printer.run(args);
    if (exitCode != 0) {
      System.exit(exitCode);
    }
  }

  public BatakjavaPrettyPrinter() {
    super("Batakjava Pretty Printer", ExtendJVersion.getVersion());
  }

  public int run(String[] args) {
    return this.run(args, Program.defaultBytecodeReader(), Program.defaultJavaParser());
  }

  @Override
  protected void processNoErrors(CompilationUnit unit) {
    try {
      unit.prettyPrint(new PrintStream(System.out, false, "UTF-8"));
    } catch (IOException e) {
      e.printStackTrace(System.err);
    }
  }

}
