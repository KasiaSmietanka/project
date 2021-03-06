package Latte.Absyn; // Java Package generated by the BNF Converter.

public class Fun extends Type {
  public final Type type_;
  public final ListType listtype_;

  public Fun(Type p1, ListType p2) { type_ = p1; listtype_ = p2; }

  public <R,A> R accept(Latte.Absyn.Type.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof Latte.Absyn.Fun) {
      Latte.Absyn.Fun x = (Latte.Absyn.Fun)o;
      return this.type_.equals(x.type_) && this.listtype_.equals(x.listtype_);
    }
    return false;
  }

  public int hashCode() {
    return 37*(this.type_.hashCode())+this.listtype_.hashCode();
  }


}
