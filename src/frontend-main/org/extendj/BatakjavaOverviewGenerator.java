package org.extendj;

import org.extendj.ast.Program;
import org.extendj.ast.BatakjavaFrontend;


public class BatakjavaOverviewGenerator extends BatakjavaFrontend {
  public static void main(String[] args) {
    int exitCode = (new BatakjavaConstraintSolver()).run(args);
    if (exitCode != 0) {
      System.exit(exitCode);
    }

  }

  public BatakjavaOverviewGenerator() {
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

}
