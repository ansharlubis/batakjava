<YYINITIAL> {
  "ver"               { return sym(Terminals.VER); }
  "prioritizes"       { return sym(Terminals.PRIORITIZES); }
  "exempts"           { return sym(Terminals.EXEMPTS); }
  "#"                 { return sym(Terminals.SHARP); }
}