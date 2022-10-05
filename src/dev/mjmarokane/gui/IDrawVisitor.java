package dev.mjmarokane.gui;

import dev.mjmarokane.model.GameWorld;
import dev.mjmarokane.model.Snake;
import dev.mjmarokane.model.Food;

public interface IDrawVisitor
{
    public void visit(GameWorld world);
    public void visit(Snake snake);
    public void visit(Food food);
}