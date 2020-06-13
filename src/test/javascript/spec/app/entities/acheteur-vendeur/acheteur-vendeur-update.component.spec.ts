import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { AcheteurVendeurUpdateComponent } from 'app/entities/acheteur-vendeur/acheteur-vendeur-update.component';
import { AcheteurVendeurService } from 'app/entities/acheteur-vendeur/acheteur-vendeur.service';
import { AcheteurVendeur } from 'app/shared/model/acheteur-vendeur.model';

describe('Component Tests', () => {
  describe('AcheteurVendeur Management Update Component', () => {
    let comp: AcheteurVendeurUpdateComponent;
    let fixture: ComponentFixture<AcheteurVendeurUpdateComponent>;
    let service: AcheteurVendeurService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [AcheteurVendeurUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AcheteurVendeurUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AcheteurVendeurUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AcheteurVendeurService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AcheteurVendeur(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new AcheteurVendeur();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
