package com.academy.ui.components;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CommentsClubComponent extends BaseComponent {


    @FindBy(xpath = "//p[@class='show-more-p']")
    private WebElement showMoreCommentsButton;

    @FindBy(xpath = "//div[@class='ant-comment-content-detail']")
    private List<WebElement> comments;

    @FindBy(xpath = ".//button[contains(@class,'answer-comment')]")
    private WebElement answerToTheCommentButton;

    @FindBy(xpath = "//ul[@class='ant-rate rating css-13m256z ant-rate-disabled']")
    private WebElement rating;

    @FindBy(xpath = ".//li[contains(@class,'ant-rate-star')]")
    private List<WebElement> ratingStars;


    public List<String> getAllComments() {
        return comments.stream().map(WebElement::getText).collect(Collectors.toList());

    }

    public CommentsClubComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

}
