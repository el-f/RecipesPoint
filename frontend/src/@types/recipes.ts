export type Recipe = {
  id: number;
  title: string;
} & Partial<{
  cheap: boolean;
  dairyFree: boolean;
  glutenFree: boolean;
  lowFodmap: boolean;
  sustainable: boolean;
  vegan: boolean;
  vegetarian: boolean;
  veryHealthy: boolean;
  veryPopular: boolean;
  pricePerServing: number;
  aggregateLikes: number;
  cookingMinutes: number;
  healthScore: number;
  preparationMinutes: number;
  readyInMinutes: number;
  servings: number;
  weightWatcherSmartPoIntegers: number;
  creditsText: string;
  gaps: string;
  image: string;
  imageType: string;
  license: string;
  sourceName: string;
  sourceUrl: string;
  spoonacularSourceUrl: string;
  summary: string;
  analyzedInstructions: AnalyzedInstruction[];
  cuisines: string[];
  diets: string[];
  dishTypes: string[];
  occasions: string[];
}>;

export type AnalyzedInstruction = {
  name: string;
  steps: Step[];
};

export type Step = {
  number: number;
  step: string;
  ingredients: Ingredient[];
  equipment: Equipment[];
};

export type Ingredient = {
  id: number;
  name: string;
  localizedName: string;
  image: string;
};

export type Equipment = {
  id: number;
  name: string;
  localizedName: string;
  image: string;
};
