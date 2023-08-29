import { atom } from "jotai";
import { RecipeQuery } from "../@types/recipe-query";

export const queryAtom = atom<RecipeQuery>({
  offset: 0,
  number: 7,
  diet: null,
  cuisine: null,
  category: null,
  freeText: null,
});
