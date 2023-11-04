FROM swaggerapi/swagger-ui:latest
MAINTAINER LibraryService

ADD /user-service/spec/user-service.yml /usr/share/nginx/html/doc/user-service.yaml
ADD /book-service/spec/book-service.yml /usr/share/nginx/html/doc/book-service.yaml
ADD /library-service/spec/library-service.yml /usr/share/nginx/html/doc/library-service.yaml

ENV URLS="[{ url: 'doc/user-service.yaml', name: 'User'},{ url: 'doc/book-service.yaml', name: 'Book'},{ url: 'doc/library-service.yaml', name: 'Library'},]"
ENV URLS_PRIMARY_NAME="Book"