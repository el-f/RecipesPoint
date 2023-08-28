import * as React from "react";
import { styled } from "@mui/material/styles";
import Card from "@mui/material/Card";
import CardHeader from "@mui/material/CardHeader";
import CardMedia from "@mui/material/CardMedia";
import CardContent from "@mui/material/CardContent";
import CardActions from "@mui/material/CardActions";
import Avatar from "@mui/material/Avatar";
import IconButton, { IconButtonProps } from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import { green } from "@mui/material/colors";
import StarBorderIcon from "@mui/icons-material/StarBorder";
import RestaurantIcon from "@mui/icons-material/Restaurant";
import { Recipe } from "../@types/recipes";
import StarIcon from "@mui/icons-material/Star";
import { useEffect, useState } from "react";
import { Tooltip } from "@mui/material";

export default function RecipeCard({ recipe }: { recipe: Recipe }) {
  const [expanded, setExpanded] = React.useState(false);
  const [favoriteSet, setFavorite] = useState(
    new Set<number>([782585, 644387, 766453])
  );

  useEffect(() => {
    console.log("favoriteSet", favoriteSet);
  }, [favoriteSet]);

  const handleExpandClick = () => {
    setExpanded(!expanded);
  };

  function handleClick() {
    if (favoriteSet.has(recipe.id)) {
      favoriteSet.delete(recipe.id);
    } else {
      favoriteSet.add(recipe.id);
    }
    setFavorite(new Set(favoriteSet));
  }

  return (
    <Card sx={cardStyle}>
      <CardHeader
        avatar={
          <Avatar sx={{ bgcolor: green[500] }}>
            <RestaurantIcon />
          </Avatar>
        }
        title={recipe.title}
        subheader="September 14, 2016"
      />
      <CardMedia
        component="img"
        height="194"
        image={recipe.image}
        alt={recipe.title}
      />
      <CardContent>
        <Typography variant="body2" color="text.secondary">
          {recipe.title}
        </Typography>
      </CardContent>
      <CardActions disableSpacing>
        {favoriteSet.has(recipe.id) ? (
          <Tooltip title="Remove from favorites">
            <IconButton
              aria-label="remove from favorites"
              onClick={handleClick}
            >
              <StarIcon />
            </IconButton>
          </Tooltip>
        ) : (
          <Tooltip title="Add to favorites">
            <IconButton aria-label="add to favorites" onClick={handleClick}>
              <StarBorderIcon />
            </IconButton>
          </Tooltip>
        )}
      </CardActions>
    </Card>
  );
}

const cardStyle = {
  height: "100%",
  display: "flex",
  flexDirection: "column",
  justifyContent: "space-between",
};

interface ExpandMoreProps extends IconButtonProps {
  expand: boolean;
}

const ExpandMore = styled((props: ExpandMoreProps) => {
  const { expand, ...other } = props;
  return <IconButton {...other} />;
})(({ theme, expand }) => ({
  transform: !expand ? "rotate(0deg)" : "rotate(180deg)",
  marginLeft: "auto",
  transition: theme.transitions.create("transform", {
    duration: theme.transitions.duration.shortest,
  }),
}));
