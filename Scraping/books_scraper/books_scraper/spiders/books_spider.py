import scrapy
from books_scraper.items import BookItem

class BooksSpider(scrapy.Spider):
    name = "books"
    start_urls = ["https://books.toscrape.com/catalogue/page-1.html"]

    def parse(self, response):
        for book in response.css("article.product_pod"):
            item = BookItem()
            item["title"] = book.css("h3 a::attr(title)").get()
            item["price"] = book.css("p.price_color::text").get()
            item["availability"] = "In" if "In stock" in book.css("p.instock.availability::text").getall()[-1].strip() else "Out"
            item["rating"] = book.css("p.star-rating::attr(class)").get().split()[1]
            item["category"] = "Books"

            yield item
        
        next_page = response.css("li.next a::attr(href)").get()
        if next_page:
            yield response.follow(next_page, self.parse)
