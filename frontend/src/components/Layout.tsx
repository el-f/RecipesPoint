import { useLocation } from 'react-router-dom';
import NavBar from './NavBar';

export function Layout({ children }: { children: React.ReactNode }) {
  const location = useLocation();
  const hideNavBar = location.pathname === '/login';

  return (
    <div style={{ display: "flex" }}>
      {!hideNavBar && (
        <div style={{ marginRight: "1rem" }}>
          <NavBar />
        </div>
      )}
      <div style={{ flex: 1 }}>{children}</div>
    </div>
  );
}
