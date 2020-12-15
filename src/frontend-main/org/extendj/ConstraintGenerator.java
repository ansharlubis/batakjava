package org.extendj;

import org.extendj.ast.Program;
import org.extendj.ast.CompilationUnit;
import org.extendj.ast.ConstraintFrontend;


public class ConstraintGenerator extends ConstraintFrontend {
  public static void main(String[] args) {
    ConstraintGenerator printer = new ConstraintGenerator();
    int exitCode = printer.run(args);
    printer.program.lookupLibraryTypeSet("upstream.collection", "Point");
    if (exitCode != 0) {
      System.exit(exitCode);
    }
  }

  public ConstraintGenerator() {
    super("Batakjava Constraint Generator", ExtendJVersion.getVersion());
  }

  public int run(String[] args) {
    return this.run(args, Program.defaultBytecodeReader(), Program.defaultJavaParser());
  }

  @Override
  protected void processNoErrors(CompilationUnit unit) { }

}