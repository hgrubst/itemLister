package controllers;

import java.io.IOException;

import models.Image;
import models.Item;

import org.apache.commons.io.FileUtils;

import play.data.Form;
import play.modules.spring.Spring;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import repositories.ItemRepository;
import views.html.index;

public class Application extends Controller {

	private static ItemRepository itemRepository = Spring.getBeanOfType(ItemRepository.class);

	public static Result index() {
		return ok(index.render("Your new application is ready.", itemRepository.findAll()));
	}

	public static Result addItem() throws IOException {

		Form<Item> itemForm = form(Item.class);

		if(itemForm.bindFromRequest().hasErrors()){
			flash("error", "Please enter a title");
			return badRequest(index.render("Invalid data.", itemRepository.findAll()));
		}
		
		Item item = itemForm.bindFromRequest().get();
		
		FilePart photo = request().body().asMultipartFormData().getFile("photo");
		if (photo != null) {
			Image image = new Image();
			image.setContentType(photo.getContentType());
			image.setFileName(photo.getFilename());
			byte[] bytes = FileUtils.readFileToByteArray(photo.getFile());
			image.setThumbnail(bytes);
			image.setSize(Long.valueOf(bytes.length));

			item.setPhoto(image);
		}

		itemRepository.save(item);

		return redirect(routes.Application.index());
	}

	public static Result getItemImage(Long id) {
		return ok(itemRepository.findOne(id).getPhoto().getThumbnail());
	}
}