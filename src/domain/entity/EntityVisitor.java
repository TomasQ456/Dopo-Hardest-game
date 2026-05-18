package domain.entity;

import domain.exception.DhgDomainException;

public interface EntityVisitor {
    void visit(Player player) throws DhgDomainException;
    void visit(Enemy enemy) throws DhgDomainException;
    void visit(YellowCoin coin) throws DhgDomainException;
    void visit(BonusCoin coin) throws DhgDomainException;
    void visit(SkinCoin coin) throws DhgDomainException;
    void visit(Bomb bomb) throws DhgDomainException;
    void visit(LifeSource source) throws DhgDomainException;
    void visit(SolidWall wall) throws DhgDomainException;
    void visit(SpecialElement element) throws DhgDomainException;
    void visit(domain.level.Zone zone) throws DhgDomainException;
}
