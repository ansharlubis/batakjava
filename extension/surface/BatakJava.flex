<YYINITIAL> {
  "ver"               { return sym(Terminals.VER); }
  "take"              { return sym(Terminals.TAKE); }
  "leave"             { return sym(Terminals.LEAVE); }
  "#"                 { return sym(Terminals.SHARP); }
}