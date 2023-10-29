let loadBooksBtn = document.getElementById('loadBooks')

loadBooksBtn.addEventListener('click', onloadBooks);

function onloadBooks(event) {

    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    let authorContainer = document.getElementById('authors-container')
    authorContainer.innerHTML = ''

    fetch("http://localhost:8080/api/books", requestOptions)
        .then(response => response.json())
        .then(json => json.forEach(book => {
            //console.log('I loaded' + book)



            let row = document.createElement('tr')

            let titleCol = document.createElement('td')
            let authorCol = document.createElement('td')
            let isbnCol = document.createElement('td')
            let actionCol = document.createElement('td')

            titleCol.textContent = book.title
            authorCol.textContent = book.author.name
            isbnCol.textContent = book.isbn


//add the columns to the parent row
            row.appendChild(titleCol)
            row.appendChild(authorCol)
            row.appendChild(isbnCol)
            row.appendChild(actionCol)

            authorContainer.appendChild(row);
        }))
        .catch(error => console.log('error', error));


}