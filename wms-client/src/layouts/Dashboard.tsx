import {Route, Routes} from "react-router-dom";
import ProductList from "../pages/products/ProductList.tsx";
import ProductAdd from "../pages/products/ProductAdd.tsx";


function Dashboard() {
    return (
        <div>
            <Routes>
                <Route path={'/products'} element={<ProductList/>}/>
                <Route path={'/products/add'} element={<ProductAdd/>}/>
            </Routes>
        </div>
    );
}

export default Dashboard;