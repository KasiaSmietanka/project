package Latte.Absyn; // Java Package generated by the BNF Converter.

public class ELitInt extends Expr {
  public final Integer integer_;

  public ELitInt(Integer p1) { integer_ = p1; }

  public <R,A> R accept(Latte.Absyn.Expr.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof Latte.Absyn.ELitInt) {
      Latte.Absyn.ELitInt x = (Latte.Absyn.ELitInt)o;
      return this.integer_.equals(x.integer_);
    }
    return false;
  }

  public int hashCode() {
    return this.integer_.hashCode();
  }


}
