export type Product = {
    id: string,
    name: string,
    description: string,
    salePrice: number,
    stock: number,
    criticalStock: number,
    imageUrl: string,
    categoryName: string,
    createdAt: Date,
    updatedAt: Date,
    active: boolean
}