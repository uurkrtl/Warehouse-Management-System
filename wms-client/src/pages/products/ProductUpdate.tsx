import {useEffect, useState} from "react";
import {Product} from "../../types/Product.ts";
import {useNavigate, useParams} from "react-router-dom";
import ProductService from "../../services/ProductService.ts";
import CategoryService from "../../services/CategoryService.ts";
import {Category} from "../../types/Category.ts";

const productService = new ProductService();
const categoryService = new CategoryService();

function ProductUpdate() {
    const { id = '' } = useParams<string>();
    const [product, setProduct] = useState<Product>({
        name: '',
        id: id,
        description: '',
        salePrice: 0,
        stock: 0,
        criticalStock: 0,
        imageUrl: '',
        categoryId: '',
        categoryName: '',
        createdAt: new Date(),
        updatedAt: new Date(),
        active: true
    });
    const navigate = useNavigate();
    const [categories, setCategories] = useState<Category[]>([]);
    const [errorMessage, setErrorMessage] = useState<string>('');

    useEffect(() => {
        categoryService.getAllCategories().then((response) => {
            setCategories(response.data);
        });
    });

    useEffect(() => {
        if (id) {
            productService.getByIdProduct(id)
                .then((response) => {
                    setProduct(response.data);
                })
                .catch((error) => {
                    console.error('Error fetching product:', error);
                    navigate('*');
                });
        }
    }, [id, navigate]);

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        productService.updateProduct(id, product)
            .then(response => {
                console.log(response)
                navigate('/products')
            })
            .catch(error => {
                if (error.response) {
                    console.log(error.response.data);
                    setErrorMessage(error.response.data.errorMessage);
                } else {
                    console.log('Something went wrong:', error.message);
                    setErrorMessage('Something went wrong: ' + error.message);
                }
            });
    };

    return (
        <div className={'container'}>
            <div className="container">
                <main>
                    <div className="py-5 text-center">
                        <svg xmlns="http://www.w3.org/2000/svg" width="72" height="72" fill="currentColor"
                             className="bi bi-handbag" viewBox="0 0 16 16">
                            <path
                                d="M8 1a2 2 0 0 1 2 2v2H6V3a2 2 0 0 1 2-2m3 4V3a3 3 0 1 0-6 0v2H3.36a1.5 1.5 0 0 0-1.483 1.277L.85 13.13A2.5 2.5 0 0 0 3.322 16h9.355a2.5 2.5 0 0 0 2.473-2.87l-1.028-6.853A1.5 1.5 0 0 0 12.64 5zm-1 1v1.5a.5.5 0 0 0 1 0V6h1.639a.5.5 0 0 1 .494.426l1.028 6.851A1.5 1.5 0 0 1 12.678 15H3.322a1.5 1.5 0 0 1-1.483-1.723l1.028-6.851A.5.5 0 0 1 3.36 6H5v1.5a.5.5 0 1 0 1 0V6z"/>
                        </svg>
                        <h2>Produktaktualisierung</h2>
                    </div>

                    <div className="row g-5">
                        <div className="col-md-12 col-lg-12">
                            <form onSubmit={handleSubmit}>
                                <div className="row g-3">

                                    <div className="col-sm-6">
                                        <label htmlFor="name" className="form-label">Produktname</label>
                                        <input type="text" className="form-control" id="name"
                                               placeholder="Schreiben Sie den Produktnamen" value={product.name}
                                               onChange={(e) => setProduct({...product, name: e.target.value})}/>
                                    </div>

                                    <div className="col-sm-6">
                                        <label htmlFor="description" className="form-label">Beschreibung</label>
                                        <input type="text" className="form-control" id="description"
                                               placeholder="Schreiben Sie die Beschreibung" value={product.description}
                                               onChange={(e) => setProduct({...product, description: e.target.value})}/>
                                    </div>

                                    <div className="col-sm-4">
                                        <label htmlFor="salePrice" className="form-label">Verkaufspreis</label>
                                        <input type="number" step="0.01" className="form-control" id="salePrice"
                                               placeholder="Schreiben Sie den Verkaufspreis" value={product.salePrice}
                                               onChange={(e) => setProduct({
                                                   ...product,
                                                   salePrice: e.target.valueAsNumber
                                               })}/>
                                    </div>

                                    <div className="col-sm-4">
                                        <label htmlFor="criticalStock" className="form-label">Kritischer
                                            Lagerbestand</label>
                                        <input type="number" className="form-control" id="criticalStock"
                                               placeholder="Schreiben Sie den kritischen Lagerbestand" value={product.criticalStock}
                                               onChange={(e) => setProduct({
                                                   ...product,
                                                   criticalStock: e.target.valueAsNumber
                                               })}/>
                                    </div>

                                    <div className="col-sm-4">
                                        <label htmlFor="categoryName" className="form-label">Kategoriename</label>
                                        <select className="form-select" id="categoryName"
                                                onChange={(e) => setProduct({...product, categoryId: e.target.value})}>
                                            <option value={product.categoryId}>{product.categoryName}</option>
                                            {categories.filter((category=>category.name!==product.categoryName)).map((category)=> {
                                                return (
                                                    <option key={category.id}
                                                            value={category.id}>{category.name}</option>
                                                )
                                            })};
                                        </select>
                                    </div>

                                    <div className="col-sm-12">
                                        <label htmlFor="imageUrl" className="form-label">Bild URL</label>
                                        <input type="text" className="form-control" id="imageUrl"
                                               placeholder="Schreiben Sie die Bild URL" value={product.imageUrl}
                                               onChange={(e) => setProduct({...product, imageUrl: e.target.value})}/>
                                    </div>
                                </div>

                                <button className="w-100 btn btn-primary btn-lg my-4" type="submit">Aktualisieren
                                </button>

                            </form>

                            {errorMessage && (
                                <div className="alert alert-danger" role="alert">
                                    {errorMessage}
                                </div>
                            )}

                        </div>
                    </div>
                </main>
            </div>
        </div>
    );
}

export default ProductUpdate;