import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Bp4InfosService } from 'app/entities/bp-4-infos/bp-4-infos.service';
import { IBp4Infos, Bp4Infos } from 'app/shared/model/bp-4-infos.model';

describe('Service Tests', () => {
  describe('Bp4Infos Service', () => {
    let injector: TestBed;
    let service: Bp4InfosService;
    let httpMock: HttpTestingController;
    let elemDefault: IBp4Infos;
    let expectedResult: IBp4Infos | IBp4Infos[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(Bp4InfosService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Bp4Infos(0, 'AAAAAAA', 'AAAAAAA', 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Bp4Infos', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Bp4Infos()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Bp4Infos', () => {
        const returnedFromService = Object.assign(
          {
            codeIsoPays: 'BBBBBB',
            libellePays: 'BBBBBB',
            mntnosCartes: 1,
            mntCartesEtr: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Bp4Infos', () => {
        const returnedFromService = Object.assign(
          {
            codeIsoPays: 'BBBBBB',
            libellePays: 'BBBBBB',
            mntnosCartes: 1,
            mntCartesEtr: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Bp4Infos', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
