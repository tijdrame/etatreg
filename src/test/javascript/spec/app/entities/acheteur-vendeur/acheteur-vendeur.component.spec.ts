import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { EtatregTestModule } from '../../../test.module';
import { AcheteurVendeurComponent } from 'app/entities/acheteur-vendeur/acheteur-vendeur.component';
import { AcheteurVendeurService } from 'app/entities/acheteur-vendeur/acheteur-vendeur.service';
import { AcheteurVendeur } from 'app/shared/model/acheteur-vendeur.model';

describe('Component Tests', () => {
  describe('AcheteurVendeur Management Component', () => {
    let comp: AcheteurVendeurComponent;
    let fixture: ComponentFixture<AcheteurVendeurComponent>;
    let service: AcheteurVendeurService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [AcheteurVendeurComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: of({
                defaultSort: 'id,asc',
              }),
              queryParamMap: of(
                convertToParamMap({
                  page: '1',
                  size: '1',
                  sort: 'id,desc',
                })
              ),
            },
          },
        ],
      })
        .overrideTemplate(AcheteurVendeurComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AcheteurVendeurComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AcheteurVendeurService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new AcheteurVendeur(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.acheteurVendeurs && comp.acheteurVendeurs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new AcheteurVendeur(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.acheteurVendeurs && comp.acheteurVendeurs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
