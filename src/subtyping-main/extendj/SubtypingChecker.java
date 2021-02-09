package org.extendj;

import org.extendj.ast.SubtypingFrontend;
import org.extendj.ast.Program;

public class SubtypingChecker extends SubtypingFrontend {
  public static void main(String[] args) {
    int exitCode = new SubtypingChecker().run(args);
    if (exitCode != 0) {
      System.exit(exitCode);
    }
  }

  public SubtypingChecker() { super("Subtyping Checker", ExtendJVersion.getVersion()); }

  public int run(String args[]) {
    return run(args, Program.defaultBytecodeReader(), Program.defaultJavaParser());
  }

}
