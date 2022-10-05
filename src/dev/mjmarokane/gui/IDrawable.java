package dev.mjmarokane.gui;

public interface IDrawable
{
    public void accept(IDrawVisitor visitor);
}