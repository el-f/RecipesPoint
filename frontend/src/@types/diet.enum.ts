export enum Diet {
  CREOLE = "Creole",
  DAIRY_FREE = "Dairy Free",
  FODMAP = "Fodmap Friendly",
  GLUTEN_FREE = "Gluten Free",
  KETOGENIC = "Ketogenic",
  LACTO_OVO_VEGETARIAN = "Lacto Ovo Vegetarian",
  LACTO_VEGETARIAN = "Lacto Vegetarian",
  OVO_VEGETARIAN = "Ovo Vegetarian",
  PALEOLITHIC = "Paleolithic",
  PRIMAL = "Primal",
  PROLETARIAN = "Proletarian",
  PESCATARIAN = "Pescatarian",
  VEGAN = "Vegan",
  VEGETARIAN = "Vegetarian",
  WHOLE30 = "Whole30",
}

export const dietAsSelectOptions = Object.values(Diet).map((diet) => ({
  label: diet,
  value: diet,
}));
