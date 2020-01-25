import { EvaluationSystemCliPage } from './app.po';

describe('evaluation-system-cli App', () => {
  let page: EvaluationSystemCliPage;

  beforeEach(() => {
    page = new EvaluationSystemCliPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
