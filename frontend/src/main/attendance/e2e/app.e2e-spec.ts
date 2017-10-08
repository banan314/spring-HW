import { AttendancePage } from './app.po';

describe('attendance App', () => {
  let page: AttendancePage;

  beforeEach(() => {
    page = new AttendancePage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
