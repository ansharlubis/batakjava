package org.extendj;

import org.extendj.ast.Program;
import org.extendj.ast.BatakjavaFrontend;

/*

Things to do:

1) Pass the variables, domains, and constraints to solver
2) Get all solutions
3) Assign each solutions back to the original variables as Map<String,Set<Integer>>
4) If FieldDecl, MethodDecl, ConstructorDecl has many variations then produce all
   those variations
5) Generate overview

*/

public class BatakjavaConstraintSolver extends BatakjavaFrontend {
  public static void main(String[] args) {
    int exitCode = (new BatakjavaConstraintSolver()).run(args);
    if (exitCode != 0) {
      System.exit(exitCode);
    }
  }

  public BatakjavaConstraintSolver() {
    super("Batakjava Overview Writer", ExtendJVersion.getVersion());
  }

  /** @deprecated */
  @Deprecated
  public static boolean compile(String[] args) {
    return 0 == (new BatakjavaConstraintSolver()).run(args);
  }

  public int run(String[] args) {
    return this.run(args, Program.defaultBytecodeReader(), Program.defaultJavaParser());
  }

  @Override
  protected void inferVersion() {

  }

}
