<YYINITIAL> {
  "version"         { return sym(Terminals.VERSION); }
  "requires"        { return sym(Terminals.REQUIRES); }

  "#"               { return sym(Terminals.SHARP); }
}