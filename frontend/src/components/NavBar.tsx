import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import { Link } from "react-router-dom";
import HomeIcon from "@mui/icons-material/Home";
import GradeIcon from "@mui/icons-material/Grade";
import RecipeFilter from "./RecipeFilter";
import { Divider } from "@mui/material";

export default function NavBar() {
  const sidebarItems = [
    { text: "Home", route: "/home", icon: <HomeIcon /> },
    { text: "Favorites", route: "/favorites", icon: <GradeIcon /> },
  ];

  const isOnHome = window.location.pathname === "/home";

  return (
    <List style={{ position: "fixed", top: "2vh", left: "2vw" }}>
      {sidebarItems.map((item) => (
        <ListItem key={item.text} disablePadding>
          <Link
            to={item.route}
            style={{
              textDecoration: "none",
              color: "inherit",
              width: "100%",
            }}
          >
            <ListItemButton selected={item.route === window.location.pathname}>
              <ListItemIcon>{item.icon}</ListItemIcon>
              <ListItemText primary={item.text} />
            </ListItemButton>
          </Link>
        </ListItem>
      ))}
      <Divider sx={{ m: 3 }} />
      {isOnHome && <RecipeFilter />}
    </List>
  );
}
