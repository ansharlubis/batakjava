package org.extendj;

import org.extendj.ast.Program;
import org.extendj.ast.CompilationUnit;
import org.extendj.ast.SolverFrontend;


public class SolverPrinter extends SolverFrontend {
  public static void main(String[] args) {
    SolverPrinter printer = new SolverPrinter();
    int exitCode = printer.run(args);
    printer.program.lookupLibraryTypeSet("upstream.collection", "Point");
    if (exitCode != 0) {
      System.exit(exitCode);
    }
  }

  public SolverPrinter() {
    super("Batakjava Constraint Generator", ExtendJVersion.getVersion());
  }

  public int run(String[] args) {
    return this.run(args, Program.defaultBytecodeReader(), Program.defaultJavaParser());
  }

  @Override
  protected void processNoErrors(CompilationUnit unit) { }

}