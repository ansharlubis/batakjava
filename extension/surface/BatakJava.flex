<YYINITIAL> {
  "ver"               { return sym(Terminals.VER); }
  "#"                 { return sym(Terminals.SHARP); }
  "take"              { return sym(Terminals.TAKE); }
  "leave"             { return sym(Terminals.LEAVE); }
}