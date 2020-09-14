import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { ChargeFileUpdateComponent } from 'app/entities/charge-file/charge-file-update.component';
import { ChargeFileService } from 'app/entities/charge-file/charge-file.service';
import { ChargeFile } from 'app/shared/model/charge-file.model';

describe('Component Tests', () => {
  describe('ChargeFile Management Update Component', () => {
    let comp: ChargeFileUpdateComponent;
    let fixture: ComponentFixture<ChargeFileUpdateComponent>;
    let service: ChargeFileService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [ChargeFileUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ChargeFileUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ChargeFileUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ChargeFileService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ChargeFile(123);
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
        const entity = new ChargeFile();
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
