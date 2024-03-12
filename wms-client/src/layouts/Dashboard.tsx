import {Route, Routes} from "react-router-dom";
import ProductList from "../pages/products/ProductList.tsx";


function Dashboard() {
    return (
        <div>
            <Routes>
                <Route path={'/products'} element={<ProductList/>}/>
            </Routes>
        </div>
    );
}

export default Dashboard;