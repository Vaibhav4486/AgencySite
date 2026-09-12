# Agency Site Backend

Backend for the company website: manages the services shown on the site,
client testimonials, and incoming contact form messages.

## What's included (fully coded)
- entity: ServiceOffering, Testimonial, ContactMessage
- repository: ServiceOfferingRepository, TestimonialRepository, ContactMessageRepository
- dto: ServiceOfferingDTO, TestimonialDTO, ContactMessageDTO
- exception: ResourceNotFound (base exception - add your own GlobalExceptionHandler as needed)
- pom.xml (Spring Boot 3.3.0, Web, Data JPA, Validation, MySQL driver, Lombok)
- application.properties (update DB credentials before running)
- Main application class

## What's left for you
- service package: ServiceOfferingService, TestimonialService, ContactService
- controller package: ServiceOfferingController, TestimonialController, ContactController

Note: the entity is named ServiceOffering, not Service — naming it "Service"
would collide with Spring's own @Service annotation in any file that imports both.
