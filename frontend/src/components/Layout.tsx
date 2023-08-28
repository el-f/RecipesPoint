import { useLocation } from "react-router-dom";
import NavBar from "./NavBar";

export function Layout({ children }: { children: React.ReactNode }) {
  const location = useLocation();
  const hideNavBar = location.pathname === "/login";

  return (
    <div>
      {!hideNavBar && <NavBar />}
      <div
        style={{
          marginLeft: hideNavBar ? 0 : 240,
        }}
      >
        {children}
      </div>
    </div>
  );
}
