// src/atoms.js
import { atom } from "jotai";
import { Recipe } from "../@types/recipe";

export const selectedRecipeAtom = atom<Recipe | null>(null);
