import {
  AppBar,
  Card,
  CardActions,
  CardContent,
  CardHeader,
  CardMedia,
  Chip,
  Grid,
  IconButton,
  Stack,
  Toolbar,
  Typography,
} from "@mui/material";
import { Recipe } from "../@types/recipe";
import CloseIcon from "@mui/icons-material/Close";
import { GridWrapperStyle, WrapperStyle, cardStyle } from "../styles/styles";
import { Link } from "react-router-dom";

export default function RecipeDetails({
  recipe,
  handleClose,
}: {
  recipe: Recipe;
  handleClose: () => void;
}) {
  return (
    <WrapperStyle>
      <AppBar position="relative">
        <Toolbar>
          <IconButton color="inherit" edge="start" onClick={handleClose}>
            <CloseIcon />
          </IconButton>
        </Toolbar>
      </AppBar>
      <GridWrapperStyle>
        <Grid container sx={{ p: 2 }} maxWidth={1200}>
          <Grid item xs={12} md={12}>
            <Card
              sx={cardStyle}
              style={{ alignItems: "center", textAlign: "left" }}
            >
              <CardHeader title={recipe.title} />
              <CardMedia
                component="img"
                image={recipe.image}
                alt={recipe.title}
                style={{
                  height: "auto",
                  width: "50%",
                }}
              />
              <CardContent sx={{ p: 2, m: 2 }}>
                <Typography>
                  {recipe.summary?.replace(/(<([^>]+)>)/gi, "")}
                </Typography>
                <Stack direction="row" spacing={1} sx={{ mt: 2 }}>
                  {recipe.vegetarian && <Chip label="Vegetarian" />}
                  {recipe.vegan && <Chip label="Vegan" />}
                  {recipe.glutenFree && <Chip label="Gluten Free" />}
                  {recipe.dairyFree && <Chip label="Dairy Free" />}
                  {recipe.veryHealthy && <Chip label="Very Healthy" />}
                  {recipe.cheap && <Chip label="Cheap" />}
                  {recipe.veryPopular && <Chip label="Very Popular" />}
                  {recipe.sustainable && <Chip label="Sustainable" />}
                  {recipe.lowFodmap && <Chip label="Low Fodmap" />}
                </Stack>
              </CardContent>
              <CardActions>
                {recipe.sourceUrl && (
                  <Link to={recipe.sourceUrl} target="_blank">
                    <Typography>• View Source Recipe</Typography>
                  </Link>
                )}
                {recipe.spoonacularSourceUrl && (
                  <Link to={recipe.spoonacularSourceUrl} target="_blank">
                    <Typography>• View Spoonacular Recipe</Typography>
                  </Link>
                )}
              </CardActions>
            </Card>
          </Grid>
        </Grid>
      </GridWrapperStyle>
    </WrapperStyle>
  );
}
