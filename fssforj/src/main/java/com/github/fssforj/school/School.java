package com.github.fssforj.school;

import com.github.fssforj.fish.Fish;

public class School
{
    private Fish[] fishes;

    public School(int schoolSize)
    {
        this.fishes = new Fish[schoolSize];
    }

    public Fish[] getFishes()
    {
        return fishes;
    }

    public void setFishes(Fish[] fishes)
    {
        this.fishes = fishes;
    }
}
