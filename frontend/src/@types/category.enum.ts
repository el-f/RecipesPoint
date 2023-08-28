export enum Category {
  ANTIPASTI = "Antipasti",
  ANTIPASTO = "Antipasto",
  APPETIZER = "Appetizer",
  BEVERAGE = "Beverage",
  BREAD = "Bread",
  BREAKFAST = "Breakfast",
  BRUNCH = "Brunch",
  CONDIMENT = "Condiment",
  DESSERT = "Dessert",
  DINNER = "Dinner",
  DIP = "Dip",
  DRINK = "Drink",
  FINGERFOOD = "Fingerfood",
  HORDOEUVRE = "Hor d'oeuvre",
  LUNCH = "Lunch",
  MAIN_COURSE = "Main course",
  MAIN_DISH = "Main dish",
  MARINADE = "Marinade",
  MORNING_MEAL = "Morning meal",
  SALAD = "Salad",
  SEASONING = "Seasoning",
  SAUCE = "Sauce",
  SIDE_DISH = "Side dish",
  SNACK = "Snack",
  SOUP = "Soup",
  SPREAD = "Spread",
  STARTER = "Starter",
}

export const categoryAsSelectOptions = Object.values(Category).map(
  (category) => ({
    label: category,
    value: category,
  })
);
