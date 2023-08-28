import { styled } from "@mui/material";

export const cardStyle = {
  height: "100%",
  display: "flex",
  flexDirection: "column",
  justifyContent: "space-between",
  minWidth: "200px",
  margin: "auto",
};

export const WrapperStyle = styled("div")(({ theme }) => ({
  background: theme.palette.background.default,
  top: 0,
  right: 0,
  position: "fixed",
  width: "100%",
  height: "100%",
  overflow: "auto",
  zIndex: 1300,
  form: {
    display: "flex",
    justifyContent: "center",
  },
}));

export const GridWrapperStyle = styled("div")(({ theme }) => ({
  display: "flex",
  justifyContent: "center",
}));